import "../App.css";
import React from "react";
import { Route, NavLink, HashRouter } from "react-router-dom";
import NavigationBar from "./NavigationBar";

function App() {
  return (
    <div className="App">
      <NavigationBar />
    </div>
  );
}

export default App;
