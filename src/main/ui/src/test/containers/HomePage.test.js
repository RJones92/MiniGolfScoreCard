import { cleanup, render } from "@testing-library/react";
import HomePage from "../../containers/HomePage";
const tournamentService = require("../../services/tournamentService");

jest.mock("../../services/tournamentService");

//TODO
beforeEach(() => {});

afterEach(() => {
  cleanup();
  jest.clearAllMocks();
});

// test:
// tabular render
// logic and maths

it("should have 3 data rows", async () => {
  // 1. render homepage
  const { findAllByRole, container } = render(<HomePage />);
  // 2. don't assert until we know getAllTournaments has sent an accepted resovled Promise
  await findAllByRole("table");
  // 3. pull out the components to assert against
  const tableBodyRows = container.querySelector("tbody").querySelectorAll("tr");
  // 4. assertion
  expect(tableBodyRows.length).toBe(3);
});

it("should only call tournament service once", async () => {
  const spy = jest.spyOn(tournamentService, "getAllTournaments");

  await render(<HomePage />);
  expect(spy).toHaveBeenCalledTimes(1);
});
