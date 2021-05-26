async function getAllTournaments() {
  const response = await fetch("/api/players");
  console.log("Request sent to retrieve all players");
  return await response.json();
}

export { getAllTournaments };
