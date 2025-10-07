"use client";

import { useState, useEffect } from "react";
import Table, { ITable, TableRow } from "../../components/Table";
import { getAllTournaments } from "../../services/tournamentService";
import { getAllScores } from "../../services/scoreService";
import { Tournament } from "src/types/tournament";
import { Course } from "src/types/course";
import { Player } from "src/types/player";
import { Score } from "src/types/score";

type TournamentTable = {
	tableName: string | number,
	table: ITable,
  tournamentWinner: string
};

function TournamentPage() {
	const [error, setError] = useState<Error>();
	const [html, setHtml] = useState<JSX.Element>(<div>Loading...</div>);

	useEffect(() => {
		Promise.all([getAllTournaments(), getAllScores()])
			.then(([allTournaments, allScores]) => {
				const tableObjects : Array<TournamentTable> = [];

				Object.keys(allTournaments).forEach((key) => {
					let tournament = allTournaments[key];
					tableObjects.push(createTournamentTable(tournament, allScores));
				});

				setHtml(createHtml(tableObjects));
			})
			.catch((error) => {
				setError(error);
			});

		function createTournamentTable(tournament : Tournament, allScores : Array<Score>) : TournamentTable {
			const players = tournament.players;				
			const tableHeaders = createTableHeaders(players);
			const tableRows = tournament.courses.map((course) =>
				createCourseRow(tournament, course, players, allScores)
			);
      
			return {
				tableName: tournament._year,
				table: {
					columnHeaders: {cellValues : tableHeaders},
					rows: tableRows
				},
				tournamentWinner: tournament.winner.firstName + " " + tournament.winner.lastName
			};
		}

		function createTableHeaders(players : Array<Player>) : Array<string> {
			const columnHeaders = ["Course"];

			const playerKeys = Object.keys(players);
			playerKeys.forEach((playerKey) => {
				const player = players[playerKey];
				const playerColumnHeader = player.firstName + " " + player.lastName;
				columnHeaders.push(playerColumnHeader);
			});

			columnHeaders.push("Course winner");

			return columnHeaders;
		}

		function createCourseRow(
			tournament: Tournament,
			course: Course,
			players: Array<Player>,
			allScores: Array<Score>
		): TableRow {
			let rowValues : Array<number | string> = [];
			// first cell
			rowValues["courseName"] = course.courseName;
			
			// score cells
			Object.keys(players).forEach((playerKey) => {
				const player = players[playerKey];
				const playerScoreForCourse: number = getScoreForCourseForPlayer(
					tournament,
					player,
					course,
					allScores
				);
				const playerName = player.firstName + " " + player.lastName;
				rowValues[playerName] = playerScoreForCourse;
			});

			const winner : Player = Object.keys(course.winnerByTournamentId)
				.filter((key) => parseInt(key) === tournament.id)
				.reduce(
					(accumulator, key) =>
						(accumulator[key] = course.winnerByTournamentId[key]),
					{}
				);

      // last cell
      let courseWinner = winner.firstName + " " + winner.lastName;
      rowValues["courseWinner"] = courseWinner;
			return {cellValues: rowValues};
		}

		function getScoreForCourseForPlayer(
			tournament : Tournament,
			player : Player,
			course : Course,
			allScores : Array<Score>
		): number {
			const allHolesForCourse = course.holes;
			let totalCourseScoreForPlayer = 0;

			allHolesForCourse.forEach((courseHole) => {
				const scoreKeys = Object.keys(allScores);
				scoreKeys.forEach((key) => {
					const score = allScores[key];
					if (
						courseHole.id === score.hole.id &&
						player.id === score.player.id &&
						tournament.id === score.tournament.id
					) {
						totalCourseScoreForPlayer += score.strokes;
					}
				});
			});

			return totalCourseScoreForPlayer;
		}

		function createHtml(tables : Array<TournamentTable>) : JSX.Element {
			return (
				<div className="container-fluid">
					{tables.map((tournamentTable, index) => (
						<div key={index}>
							<h2>{tournamentTable.tableName}</h2>
							<p>Winner: {tournamentTable.tournamentWinner}</p>
							<div className="row">
								<div className="col-md-6">
									<div>
										<Table
											key={tournamentTable.tableName}
											columnHeaders={tournamentTable.table.columnHeaders}
											rows={tournamentTable.table.rows}
										/>
									</div>
								</div>
							</div>
						</div>
					))}
				</div>
			);
		}

	}, []);



	if (error) {
		return <div>Error: {error.message}</div>;
	} else {
		return html;		
	}
}

export default TournamentPage;
