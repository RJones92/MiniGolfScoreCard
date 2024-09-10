"use client";

import { useEffect, useState } from "react";
import Table, { TableRow } from "../components/Table";
import { getAllTournaments } from "../services/tournamentService";
import { Tournament } from "src/types/tournament";

function HomePage() {
	const [rows, setRows] = useState<Array<TableRow>>([]);
	const [isLoaded, setIsLoaded] = useState(false);
	const [error, setError] = useState<Error>();
	const columnHeaders = ["Tournament", "Winner"];

	useEffect(() => {
		getAllTournaments()
			.then((allTournaments) => {
				setIsLoaded(true);
				createRows(allTournaments);
			})
			.catch((error) => {
				setIsLoaded(true);
				setError(error);
			});

		function createRows(tournaments: Array<Tournament>) {
      let formattedRows : Array<TableRow> = [];

			Object.keys(tournaments).forEach((key) => {
				let tournament = tournaments[key];
				let winner =
					tournament.winner.firstName + " " + tournament.winner.lastName;
				let newRow : TableRow = {
          cellValues: [tournament._year, winner]
				};
        formattedRows.push(newRow);
			})
      setRows(formattedRows);
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
						<Table columnHeaders={columnHeaders} rows={rows} />
					</div>
				</div>
			</div>
		);
	}
}

export default HomePage;
