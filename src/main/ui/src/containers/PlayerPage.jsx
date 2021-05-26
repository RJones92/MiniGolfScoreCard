import React, { useEffect, useState } from "react";
import { getAllPlayers } from "../services/playerService";

function PlayerPage(props) {
  const [isLoaded, setIsLoaded] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    getAllPlayers().then(
      (allPlayers) => {
        setIsLoaded(true);
        //DO STUFF
      },
      (errorMessage) => {
        setIsLoaded(true);
        setError(errorMessage);
      }
    );
  }, []);

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return <p>Placeholder - not yet implemented.</p>;
  }
}

export default PlayerPage;
