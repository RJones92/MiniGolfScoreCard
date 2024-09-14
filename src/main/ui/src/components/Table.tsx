import React from 'react';

export interface ITable {
  columnHeaders: TableRow,
  rows: Array<TableRow>,
}

export interface TableRow {
  cellValues: Array<string | number>
}

function Table({ columnHeaders, rows } : ITable) {
  const tableRows : Array<Array<JSX.Element>> = [];
  createRows();

  function createRows() {
    rows.forEach((row : TableRow, index) => {
      let tableDataElements : Array<JSX.Element> = [];

      Object.keys(row.cellValues).forEach((key, columnIndex) => {
        let elementKey : string | number = index + columnIndex;
        let elementText : string = row.cellValues[key];
        let element = <td key={elementKey}>{elementText}</td>;
        tableDataElements.push(element);
      });

      tableRows.push(tableDataElements);
    });

  }

  return (
    <table className='table mt-4'>
      <thead>
        <tr key='columnHeaders'>
          {columnHeaders.cellValues.map((headerName : string) => (
            <th key={headerName} scope='col'>
              {headerName}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {tableRows.map((row, index) => (
          <tr key={index}>{row}</tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;
