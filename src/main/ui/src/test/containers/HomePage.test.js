import React from "react";
import ReactDOM, { unmountComponentAtNode } from "react-dom";
import { cleanup, render } from "@testing-library/react";
import HomePage from "../../containers/HomePage";
const tournamentService = require("../../services/tournamentService");

jest.mock("../../services/tournamentService");

afterEach(() => {
  cleanup();
  jest.clearAllMocks();
});

// TODO test cell contents are expected

it("renders without crashing", async () => {
  const div = document.createElement("div");
  ReactDOM.render(<HomePage />, div);
  unmountComponentAtNode(div);
});

it("should have 3 data rows and one header row", async () => {
  const { findAllByRole } = render(<HomePage />);
  const tableRows = await findAllByRole("row");
  expect(tableRows.length).toBe(4);
});

it("should have the expected cell contents in table", async () => {
  const { findAllByRole } = render(<HomePage />);
  const tableRows = await findAllByRole("row");

  const headerRow = tableRows[0];
  expect(headerRow.childElementCount).toBe(2);
  expect(headerRow.children.item(0)).toHaveTextContent("Tournament");
  expect(headerRow.children.item(1)).toHaveTextContent("Winner");

  const dataRows = tableRows.slice(1);
  expect(dataRows.length).toBe(3);
  expect(dataRows[0].children[0]).toHaveTextContent("2016");
  expect(dataRows[0].children[1]).toHaveTextContent("Rhys A");
  expect(dataRows[1].children[0]).toHaveTextContent("2017");
  expect(dataRows[1].children[1]).toHaveTextContent("Jamie C");
  expect(dataRows[2].children[0]).toHaveTextContent("2018");
  expect(dataRows[2].children[1]).toHaveTextContent("Tom B");
});

it("should only call tournament service once", async () => {
  const spy = jest.spyOn(tournamentService, "getAllTournaments");
  const { findByRole } = render(<HomePage />);
  // don't assert until we know getAllTournaments has sent an accepted resovled Promise
  await findByRole("table");
  expect(spy).toHaveBeenCalledTimes(1);
});
