import React from "react";

function Table(props) {
  //props.columnHeaders = [header1, header2, header3, etc]
  //props.rows = [{valueCol1, valueCol2, valueCol3}, {valueCol1, valueCol2, valueCol3}, etc]

  const tableRows = [];
  createRows();

  function createRows() {
    props.rows.forEach((row, index) => {
      let tableDataElements = [];
      for (const colValue in row) {
        tableDataElements.push(
          <td key={index + row[colValue]}>{row[colValue]}</td>
        );
      }
      tableRows.push(tableDataElements);
    });
  }

  return (
    <table className="table mt-4">
      <thead>
        <tr key="columnHeaders">
          {props.columnHeaders.map((headerName) => (
            <th key={headerName} scope="col">
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
