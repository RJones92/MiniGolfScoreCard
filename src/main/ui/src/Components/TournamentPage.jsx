import React, { useState, useEffect } from "react";
import Table from "./Table";
import { getAllTournaments } from "../services/tournamentService";
import { getAllScores } from "../services/scoreService";

function TournamentPage(props) {
  const [tournamentTableObjects, setTournamentTableObjects] = useState([]);
  //[{colHeader1, colHeader2, colHeader3}, {rowVal1, rowVal2, rowVal3}, {rowVal1, rowVal2, rowVal3}]
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

        console.log(tableObjects);
        setTournamentTableObjects(tableObjects);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );

    function createTournamentTable(tournament, allScores) {
      let players = tournament.players;
      let tableHeaders = createTableHeaders(players);
      let tableRows = tournament.courses.map((course) =>
        createCourseRow(course, players, allScores)
      );
      return { headers: tableHeaders, rows: tableRows };
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

    function createCourseRow(course, players, allScores) {
      let newRow = { courseName: course.courseName };

      players.forEach((player) => {
        let playerScoreForCourse = getScoreForCourseForPlayer(
          player,
          course,
          allScores
        );
        let playerName = player.firstName + " " + player.lastName;
        newRow[playerName] = playerScoreForCourse;
      });

      return newRow;
    }

    function getScoreForCourseForPlayer(player, course, allScores) {
      const allHolesForCourse = course.holes;
      let totalCourseScoreForPlayer = 0;

      allHolesForCourse.forEach((courseHole) => {
        let scoreKeys = Object.keys(allScores);
        scoreKeys.forEach((key) => {
          let score = allScores[key];
          if (
            courseHole.id === score.hole.id &&
            player.id === score.player.id
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
          <div className="row">
            <div className="col-md-6">
              <div key={index}>
                <Table
                  columnHeaders={tournamentTableObject.headers}
                  rows={tournamentTableObject.rows}
                />
              </div>
            </div>
          </div>
        ))}
      </div>
    );
  }
}

export default TournamentPage;
