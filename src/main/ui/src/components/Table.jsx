import React from "react";

function Table(props) {
  const { columnHeaders, rows } = props;
  //props.columnHeaders = [header1, header2, header3, etc]
  //props.rows = [{valueCol1, valueCol2, valueCol3}, {valueCol1, valueCol2, valueCol3}, etc]

  const tableRows = [];
  createRows();

  function createRows() {
    console.log(props);
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
    <table className="table mt-4">
      <thead>
        <tr key="columnHeaders">
          {columnHeaders.map((headerName) => (
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
