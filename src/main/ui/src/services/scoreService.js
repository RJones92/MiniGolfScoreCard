async function getAllScores() {
  const response = await fetch('/api/scores/');
  console.log('Request sent to retrieve all scores');
  return await response.json();
}

export { getAllScores };
