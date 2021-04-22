import React, { useState, useEffect } from "react";
import Table from "../components/Table";
import { getAllTournaments } from "../services/tournamentService";
import { getAllScores } from "../services/scoreService";

function TournamentPage(props) {
  const [tournamentTableObjects, setTournamentTableObjects] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    Promise.all([getAllTournaments(), getAllScores()]).then(
      ([allTournaments, allScores]) => {
        setIsLoaded(true);
        const tableObjects = [];

        const tournamentKeys = Object.keys(allTournaments);
        tournamentKeys.forEach((key) => {
          let tournament = allTournaments[key];
          tableObjects.push(createTournamentTable(tournament, allScores));
        });

        setTournamentTableObjects(tableObjects);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );

    function createTournamentTable(tournament, allScores) {
      const players = tournament.players;
      const tableName = tournament.year;
      const tournamentWinner =
        tournament.winner.firstName + " " + tournament.winner.lastName;
      const tableHeaders = createTableHeaders(players);
      const tableRows = tournament.courses.map((course) =>
        createCourseRow(tournament, course, players, allScores)
      );
      return {
        tableName: tableName,
        headers: tableHeaders,
        rows: tableRows,
        tournamentWinner: tournamentWinner,
      };
    }

    function createTableHeaders(players) {
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

    function createCourseRow(tournament, course, players, allScores) {
      const newRow = { courseName: course.courseName };

      const playerKeys = Object.keys(players);
      playerKeys.forEach((playerKey) => {
        const player = players[playerKey];
        const playerScoreForCourse = getScoreForCourseForPlayer(
          tournament,
          player,
          course,
          allScores
        );
        const playerName = player.firstName + " " + player.lastName;
        newRow[playerName] = playerScoreForCourse;
      });

      const tournamentId = tournament.id;
      const winner = Object.keys(course.winnersByTournamentId)
        .filter((key) => key == tournamentId)
        .reduce(
          (accumulator, key) =>
            (accumulator[key] = course.winnersByTournamentId[key]),
          {}
        );
      console.log(winner);
      newRow["courseWinner"] = winner.firstName + " " + winner.lastName;
      //TODO - does this work?
      //I doubt it, the 'winner' is a full object. Really need to take their first and last name first

      return newRow;
    }

    function getScoreForCourseForPlayer(tournament, player, course, allScores) {
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
  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div className="container-fluid">
        {tournamentTableObjects.map((tournamentTableObject, index) => (
          <div>
            <h2>{tournamentTableObject.tableName}</h2>
            <p>Winner: {tournamentTableObject.tournamentWinner}</p>
            <div className="row">
              <div className="col-md-6">
                <div key={index}>
                  <Table
                    columnHeaders={tournamentTableObject.headers}
                    rows={tournamentTableObject.rows}
                    tableHeader={tournamentTableObject.year}
                  />
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    );
  }
}

export default TournamentPage;
