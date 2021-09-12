import { getAllPlayers } from "../../services/playerService";

const mockResponse = [
  {
    id: 1,
    firstName: "Rhys",
    lastName: "A",
    countOfTournamentsPlayed: 3,
    countOfTournamentsWon: 0,
    countOfCoursesPlayed: 20,
    countOfCoursesWon: 0,
    countOfHolesPlayed: 316,
    countOfHolesWon: 127,
  },
  {
    id: 2,
    firstName: "Thomas",
    lastName: "B",
    countOfTournamentsPlayed: 3,
    countOfTournamentsWon: 0,
    countOfCoursesPlayed: 20,
    countOfCoursesWon: 0,
    countOfHolesPlayed: 316,
    countOfHolesWon: 125,
  },
  {
    id: 3,
    firstName: "Jamie",
    lastName: "C",
    countOfTournamentsPlayed: 3,
    countOfTournamentsWon: 3,
    countOfCoursesPlayed: 20,
    countOfCoursesWon: 20,
    countOfHolesPlayed: 316,
    countOfHolesWon: 210,
  },
  {
    id: 4,
    firstName: "Jade",
    lastName: "D",
    countOfTournamentsPlayed: 0,
    countOfTournamentsWon: 0,
    countOfCoursesPlayed: 0,
    countOfCoursesWon: 0,
    countOfHolesPlayed: 0,
    countOfHolesWon: 0,
  },
];

beforeEach(() => {
  fetch.resetMocks();
});

test("returns a list of players", async () => {
  fetch.mockResponseOnce(JSON.stringify(mockResponse));
  const allPlayers = await getAllPlayers();
  expect(allPlayers.length).toBe(4);
  expect(fetch.mock.calls.length).toBe(1);
});
