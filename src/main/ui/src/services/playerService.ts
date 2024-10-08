import { Player } from "src/types/player";

async function getAllPlayers(): Promise<Array<Player>> {
  const response = await fetch('http://localhost:8080/api/players');
  console.log('Request sent to retrieve all players');
  return await response.json();
}

export { getAllPlayers };
