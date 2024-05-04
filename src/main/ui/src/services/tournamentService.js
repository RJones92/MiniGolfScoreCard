async function getAllTournaments() {
  const response = await fetch('/api/tournaments');
  console.log('Request sent to retrieve all tournament');
  return await response.json();
}

export { getAllTournaments };
