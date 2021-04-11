async function getAllScores() {
  const response = await fetch("/api/scores/");
  console.log("Request sent to retrieve all scores");
  return await response.json();
}

async function getScoresByTournamentYear(tournamentYear) {
  const response = await fetch("/api/scores/tournament/" + tournamentYear);
  console.log(
    "Request sent to retrieve scores for tournament year " + tournamentYear
  );
  return await response.json();
}

export { getAllScores, getScoresByTournamentYear };
