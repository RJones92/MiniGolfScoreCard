'use client';

import React, { useEffect, useState } from 'react';
import Table, { ITable, TableRow } from '../../components/Table';
import { getAllPlayers } from '../../services/playerService';
import { Player } from 'src/types/player';

const statTypes = new Map([
  ['countOfTournamentsPlayed', 'Tournaments played'],
  ['countOfTournamentsWon', 'Tournaments won'],
  ['countOfCoursesPlayed', 'Courses played'],
  ['countOfCoursesWon', 'Courses won'],
  ['countOfHolesPlayed', 'Holes played'],
  ['countOfHolesWon', 'Holes won'],
]);

function PlayerPage() {
  const [error, setError] = useState<Error>();
  const [html, setHtml] = useState<JSX.Element>(<div>Loading...</div>);

  useEffect(() => {
    getAllPlayers().then(
      (allPlayers) => {
        let statsTable = createStatsTable(allPlayers);
        setHtml(createHtml(statsTable));
      })
      .catch((error) => {
        setError(error);
      });

    function createStatsTable(allPlayers : Array<Player>) : ITable {
      const tableColumns = createStatsTableColumns(allPlayers);
      const tableAsRows = transposeColumnsToRows(tableColumns);

      let headerRow : TableRow = tableAsRows[0];
      let otherRows : Array<TableRow> = [];
      for (let i = 1; i < tableAsRows.length; i++) {
        otherRows.push(tableAsRows[i]);
      }

      return {
        columnHeaders : headerRow,
        rows: [...otherRows],
      };

    }

    function createStatsTableColumns(allPlayers : Array<Player>) {
      const playerColumns : Array<Array<string>> = Object.keys(allPlayers).map((playerKey) =>
        createPlayerColumn(allPlayers[playerKey])
      );

      return {
        headerColumn: ['', ...statTypes.values()],
        playerColumns: playerColumns,
      };
    }

    function createPlayerColumn(player : Player) : Array<string> {
      const column : Array<string> = [];
      column.push(player.firstName + ' ' + player.lastName);

      for (const statName of statTypes.keys()) {
        column.push(player.playerStats[statName]);
      }

      return column;
    }

    function transposeColumnsToRows(tableColumns : {headerColumn : Array<string>, playerColumns : Array<Array<string>>}) : Array<TableRow> {
      let rows : Array<TableRow> = [];
      for (let i = 0; i < tableColumns.headerColumn.length; i++) {
        let row : Array<string> = [];
        row.push(tableColumns.headerColumn[i]);

        for (let x = 0; x < tableColumns.playerColumns.length; x++) {
          row.push(tableColumns.playerColumns[x][i]);
        }

        rows.push({cellValues : row});
      }

      return rows;
    }

    function createHtml(table : ITable) : JSX.Element {
      return (
        <div className='container-fluid'>
        <div className='row'>
          <div className='col-md-6'>
            <Table
              columnHeaders={table.columnHeaders}
              rows={table.rows}
            />
          </div>
        </div>
      </div>
      )
    }

  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else {
    return html;
  }
}

export default PlayerPage;
