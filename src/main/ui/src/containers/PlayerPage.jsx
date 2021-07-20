import React, { useEffect, useState } from "react";
import Table from "../components/Table";
import { getAllPlayers } from "../services/playerService";

const statTypes = new Map([
  ["countOfTournamentsPlayed", "Tournaments played"],
  ["countOfTournamentsWon", "Tournaments won"],
  ["countOfCoursesPlayed", "Courses played"],
  ["countOfCoursesWon", "Courses won"],
  ["countOfHolesPlayed", "Holes played"],
  ["countOfHolesWon", "Holes won"],
]);

function PlayerPage(props) {
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const [playerStatsTableObjects, setPlayerStatsTableObjects] = useState([]);

  useEffect(() => {
    getAllPlayers().then(
      (allPlayers) => {
        let statsTable = createStatsTable(allPlayers);
        setPlayerStatsTableObjects(statsTable);
        setIsLoaded(true);
      },
      (error) => {
        setError(error);
        setIsLoaded(true);
      }
    );

    function createStatsTable(allPlayers) {
      const tableColumns = createStatsTableColumns(allPlayers);
      const tableAsRows = transposeColumnsToRows(tableColumns);

      let headerRow = [];
      let otherRows = [];
      for (let i = 0; i < tableAsRows.length; i++) {
        if (i === 0) {
          headerRow = tableAsRows[0];
        } else {
          otherRows.push(tableAsRows[i]);
        }
      }

      return {
        headers: headerRow,
        rows: otherRows,
      };
    }

    function createStatsTableColumns(allPlayers) {
      const playerKeys = Object.keys(allPlayers);
      const playerColumns = playerKeys.map((playerKey) =>
        createPlayerColumn(allPlayers[playerKey])
      );

      return {
        headerColumn: ["", ...statTypes.values()],
        playerColumns: playerColumns,
      };
    }

    function createPlayerColumn(player) {
      const column = [];
      column.push(player.firstName + " " + player.lastName);

      for (const statName of statTypes.keys()) {
        column.push(player[statName]);
      }

      return column;
    }

    function transposeColumnsToRows(tableColumns) {
      let rows = [];
      for (let i = 0; i < tableColumns.headerColumn.length; i++) {
        let row = [];
        row.push(tableColumns.headerColumn[i]);

        for (let x = 0; x < tableColumns.playerColumns.length; x++) {
          row.push(tableColumns.playerColumns[x][i]);
        }

        rows.push(row);
      }

      return rows;
    }
  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    console.log(playerStatsTableObjects);
    return (
      // <p>placeholder</p>
      <div className="container-fluid">
        <div className="row">
          <div className="col-md-6">
            <Table
              columnHeaders={playerStatsTableObjects.headers}
              rows={playerStatsTableObjects.rows}
              tableHeader="testing"
            />
          </div>
        </div>
      </div>
    );
  }
}

export default PlayerPage;
