import React, { useEffect, useState } from "react";
import Table from "./Table";
import { getAllTournaments } from "../services/tournamentService";

function HomePage() {
  const [formattedRowObjects, setFormattedRowObjects] = useState([]);
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const columnHeaders = ["Tournament", "Winner"];

  useEffect(() => {
    getAllTournaments().then(
      (response) => {
        setIsLoaded(true);
        createRows(response.tournaments);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );

    function createRows(tournaments) {
      tournaments.forEach((tournament) => {
        let newRow = {
          year: tournament.year,
          winner: tournament.winner.firstName,
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
            <Table columnHeaders={columnHeaders} rows={formattedRowObjects} />
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
