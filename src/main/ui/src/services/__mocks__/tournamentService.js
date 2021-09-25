const mockAllTournamentsResponse = [
  {
    id: 1,
    year: "2016",
    courses: [],
    players: [],
    winner: {
      id: 1,
      firstName: "Rhys",
      lastName: "A",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
  {
    id: 2,
    year: "2017",
    courses: [],
    players: [],
    winner: {
      id: 3,
      firstName: "Jamie",
      lastName: "C",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
  {
    id: 3,
    year: "2018",
    courses: [],
    players: [],
    winner: {
      id: 2,
      firstName: "Tom",
      lastName: "B",
      countOfTournamentsPlayed: 0,
      countOfTournamentsWon: 0,
      countOfCoursesPlayed: 0,
      countOfCoursesWon: 0,
      countOfHolesPlayed: 0,
      countOfHolesWon: 0,
    },
  },
];

async function getAllTournaments() {
  return new Promise((resolve) => {
    resolve(mockAllTournamentsResponse);
  });
}

export { getAllTournaments };
