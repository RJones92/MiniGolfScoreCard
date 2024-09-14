import { Tournament } from "src/types/tournament";

async function getAllTournaments() : Promise<Array<Tournament>> {
  const response = await fetch('http://localhost:8080/api/tournaments');
  console.log('Request sent to retrieve all tournament');
  return await response.json();
}

export { getAllTournaments };
