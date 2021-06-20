import React, { useEffect, useState } from "react";
import { getAllPlayers } from "../services/playerService";

const statTypes = new Map([
  ["countOfTournamentsPlayed", "Tournaments played"],
  ["countOfTournamentsWon", "Tournaments won"],
  ["countOfCoursesPlayed", "Courses played"],
  ["countOfCoursesWon", "Courses won"],
  ["countOfHolesPlayed", "Holes played"],
  ["countOfHolesWon", "Holes won"],
]);

function PlayerPage(props) {
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);
  const [playerStatsTableObjects, setPlayerStatsTableObjects] = useState([]);

  useEffect(() => {
    getAllPlayers().then(
      (allPlayers) => {
        setIsLoaded(true);
        setPlayerStatsTableObjects(createStatsTable(allPlayers));
      },
      (errorMessage) => {
        setIsLoaded(true);
        setError(errorMessage);
      }
    );

    function createStatsTable(allPlayers) {
      const playerKeys = Object.keys(allPlayers);
      let playerColumns = playerKeys.forEach((playerKey) => {
        const player = allPlayers[playerKey];
        return createPlayerColumn(player);
      });

      return {
        headerColumn: ["", ...statTypes.values()],
        playerColumns: playerColumns,
      };
    }

    function createPlayerColumn(player) {
      const column = [];
      column.push(player.firstName + " " + player.lastName);

      for (const statName of statTypes.values()) {
        column.push(statName);
      }

      return column;
    }
  }, [playerStatsTableObjects]);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return <p>Placeholder - not yet implemented.</p>;
  }
}

export default PlayerPage;
