"use client";

import { useEffect, useState } from "react";
import Table from "../components/Table";
import { getAllTournaments } from "../services/tournamentService";
import { Tournament } from "src/types/tournament";

type formattedRow = {
  year: number,
  winner: string
}

function HomePage() {
	const [formattedRowObjects, setFormattedRowObjects] = useState<Array<formattedRow>>([]);
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
      let formattedRows : Array<formattedRow> = [];

			Object.keys(tournaments).forEach((key) => {
				let tournament = tournaments[key];
				let winner =
					tournament.winner.firstName + " " + tournament.winner.lastName;
				let newRow : formattedRow = {
					year: tournament._year,
					winner: winner,
				};
        formattedRows.push(newRow);
			})
      setFormattedRowObjects(formattedRows);
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

export default HomePage;
