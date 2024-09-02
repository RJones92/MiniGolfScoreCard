import React from 'react';

function Table({ columnHeaders, rows }) {
  const tableRows = [];
  createRows();

  function createRows() {
    rows.forEach((row, index) => {
      let tableDataElements = [];

      const keys = Object.keys(row);
      keys.forEach((key, columnIndex) => {
        tableDataElements.push(<td key={index + columnIndex}>{row[key]}</td>);
      });

      tableRows.push(tableDataElements);
    });
  }

  return (
    <table className='table mt-4'>
      <thead>
        <tr key='columnHeaders'>
          {columnHeaders.map((headerName) => (
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
