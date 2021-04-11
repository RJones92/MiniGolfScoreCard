import "../App.css";
import React from "react";
import NavigationBar from "./NavigationBar";
import HomePage from "./HomePage";
import TournamentPage from "./TournamentPage";

function App() {
  return (
    <div className="App">
      <NavigationBar />
      {/* <HomePage /> */}
      <TournamentPage />
    </div>
  );
}

export default App;
