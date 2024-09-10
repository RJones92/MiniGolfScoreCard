import { Score } from "src/types/score";

async function getAllScores() : Promise<Array<Score>> {
  const response = await fetch('http://localhost:8080/api/scores/');
  console.log('Request sent to retrieve all scores');
  return await response.json();
}

export { getAllScores };
