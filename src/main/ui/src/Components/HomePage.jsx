import React from "react";
import Table from "./Table";

function HomePage(props) {
  let rows = dummyRowDataGeneration();
  let cols = ["Tournament", "Winner"];

  return (
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6">
          <Table columnHeaders={cols} rows={rows} />
        </div>
      </div>
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

export default HomePage;
