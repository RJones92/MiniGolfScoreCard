import React from "react";

function Table(props) {
  //props.columnHeaders = [header1, header2, header3, etc]
  //props.rows = [{valueCol1, valueCol2, valueCol3}, {valueCol1, valueCol2, valueCol3}, etc]

  const tableRows = [];
  createRows();

  function createRows() {
    props.rows.forEach((row) => {
      let tableDataElements = [];
      for (const colValue in row) {
        tableDataElements.push(<td>{row[colValue]}</td>);
      }
      tableRows.push(tableDataElements);
    });
  }

  return (
    <table class="table mt-4">
      <thead>
        <tr>
          {props.columnHeaders.map((headerName) => (
            <th scope="col">{headerName}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {tableRows.map((row) => (
          <tr>{row}</tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;
