import React, { useState, useEffect } from "react";
import Table from "../commonComponents/Table";
import { getAllTournaments } from "../../services/tournamentService";
import { getAllScores } from "../../services/scoreService";

function TournamentPage(props) {
  const [tournamentTableObjects, setTournamentTableObjects] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    Promise.all([getAllTournaments(), getAllScores()]).then(
      ([allTournaments, allScores]) => {
        setIsLoaded(true);
        let tableObjects = [];

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
      let players = tournament.players;
      let tableName = tournament.year;
      let tournamentWinner =
        tournament.winner.firstName + " " + tournament.winner.lastName;
      let tableHeaders = createTableHeaders(players);
      let tableRows = tournament.courses.map((course) =>
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
      let columnHeaders = ["Course"];

      const playerKeys = Object.keys(players);
      playerKeys.forEach((key) => {
        let player = players[key];

        let columnHeader = player.firstName + " " + player.lastName;

        columnHeaders.push(columnHeader);
      });
      return columnHeaders;
    }

    function createCourseRow(tournament, course, players, allScores) {
      let newRow = { courseName: course.courseName };

      players.forEach((player) => {
        // need to account for tournament year
        let playerScoreForCourse = getScoreForCourseForPlayer(
          tournament,
          player,
          course,
          allScores
        );
        let playerName = player.firstName + " " + player.lastName;
        newRow[playerName] = playerScoreForCourse;
      });

      return newRow;
    }

    function getScoreForCourseForPlayer(tournament, player, course, allScores) {
      const allHolesForCourse = course.holes;
      let totalCourseScoreForPlayer = 0;

      allHolesForCourse.forEach((courseHole) => {
        let scoreKeys = Object.keys(allScores);
        scoreKeys.forEach((key) => {
          let score = allScores[key];
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
