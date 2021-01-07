import "../App.css";
import React from "react";
import NavigationBar from "./NavigationBar";
import Table from "./Table";

function App() {
  let rows = dummyRowDataGeneration();
  let cols = ["Tournament", "Winner"];

  return (
    <div className="App">
      <NavigationBar />
      <Table columnHeaders={cols} rows={rows} />
    </div>
  );
}

function dummyRowDataGeneration() {
  let row1 = {
    val1: "2016",
    val2: "Rhys",
  };
  let row2 = {
    val1: "2017",
    val2: "Tom",
  };
  let row3 = {
    val1: "2018",
    val2: "Jamie",
  };
  return [row1, row2, row3];
}

export default App;
