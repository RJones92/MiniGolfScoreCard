import React, { useEffect, useState } from "react";
import Table from "../components/Table";
import { getAllTournaments } from "../services/tournamentService";

function HomePage() {
  const [formattedRowObjects, setFormattedRowObjects] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const columnHeaders = ["Tournament", "Winner"];

  useEffect(() => {
    getAllTournaments().then(
      (allTournaments) => {
        setIsLoaded(true);
        createRows(allTournaments);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );

    function createRows(tournaments) {
      let tournamentKeys = Object.keys(tournaments);
      tournamentKeys.forEach((key) => {
        let tournament = tournaments[key];
        let winner =
          tournament.winner.firstName + " " + tournament.winner.lastName;
        let newRow = {
          year: tournament.year,
          winner: winner,
        };

        setFormattedRowObjects((formattedRowObjects) => [
          ...formattedRowObjects,
          newRow,
        ]);
      });
    }
  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (
      <div className="container-fluid">
        <div className="row">
          <div className="col-md-6">
            <Table
              columnHeaders={columnHeaders}
              rows={formattedRowObjects}
              tableHeader="testing"
            />
          </div>
        </div>
      </div>
    );
  }
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
