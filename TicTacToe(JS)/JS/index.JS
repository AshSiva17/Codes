/**
 * Ashwin Sivabalan 400591520
 * February 6th 2025
 * Javascript code for Tic Tac Toe game. Includes all functionality and rules
 */


//input variables, 1 for first player and 2 for second player
let p1NameAge;
let p2NameAge;
let p1Colour;
let p2Colour;

//variables that hold the player scores
let winCount1 = 0;
let winCount2 = 0;

//array where the value of each cell represents a symbol on the board. Default set to w for white.
let board = [['w', 'w', 'w'], ['w', 'w', 'w'], ['w', 'w', 'w']]

//variable that determines which players turn it is (1 for player1 and 2 for player2)
let turn = 1;

//variable that holds the winner of the game 
let win;
class Player {
    /**
     * Construct a new player
     * @param {string} name 
     * @param {string} colour 
     * @param {number} age 
     */
    constructor(name, colour, age = "0") {
        this.name = name;
        this.colour = colour;
        this.age = age;
    }

    /**
     * @returns DOM rendering of a player object into a <div> element
     */
    render() {
        // create the outer div
        let div = document.createElement("div");
        div.classList.add("player");

        // create and append the heading
        let h2 = document.createElement("h2");
        h2.innerHTML = this.name;
        div.appendChild(h2);

        // create and append the ul for rank and colour number
        let ul = document.createElement("ul");
        let li1 = document.createElement("li");
        li1.innerHTML = "Colour: " + this.colour;
        let li2 = document.createElement("li");
        li2.innerHTML = "Age #: " + this.age;
        ul.appendChild(li1);
        ul.appendChild(li2);
        div.appendChild(ul);

        // return the result
        return div;
    }
}


window.addEventListener("load", function (event) {
    // the model (array of players)
    players = [];

    // input fields 
    let namefield = document.getElementById("name");
    let colourfield = document.getElementById("colour");
    let agefield = document.getElementById("age");
    //holds player info
    let playerbox = document.getElementById("players");

    function filled(event) {
        if (namefield.value !== "" && agefield.value !== "") {
            document.getElementById("button1").disabled = false;
        } else {
            document.getElementById("button1").disabled = true;
        }
    };
    namefield.addEventListener("input", function (event) {
        filled();
    });
    agefield.addEventListener("input", function (event) {
        filled();
    });

    //Button listener to add and render players
    document.getElementById("button1")
        .addEventListener("click", function (event) {
            // create player and add to array
            let p = new Player(namefield.value, colourfield.value, parseInt(agefield.value));
            players.push(p);

            if (players.length > 0) {
                button2.disabled = false;
            }
            if (players.length === 2) {
                namefield.disabled = true;
                agefield.disabled = true;
                colourfield.disabled = true;
                button1.disabled = true;
                button3.disabled = false;
            }

            // render the players
            playerbox.innerHTML = "";
            for (let player of players) {
                playerbox.appendChild(player.render());
            }

            if (players.length === 1) {
                p1NameAge = players[0].name + "(" + players[0].age + ")";
                p1Colour = players[0].colour;

            } else {
                p2NameAge = players[1].name + "(" + players[1].age + ")";
                p2Colour = players[1].colour;
            }

            namefield.value = "";
            agefield.value = "";
            colour.value = "#000000";
            filled();

        });


    //removes a player (log out)
    document.getElementById("button2").addEventListener("click", function (event) {
        players.pop()
        playerbox.innerHTML = "";
        for (let player of players) {
            playerbox.appendChild(player.render());
        }
        if (players.length === 0) {
            button2.disabled = true;
        }
        if (players.length < 2) {
            namefield.disabled = false;
            agefield.disabled = false;
            colourfield.disabled = false;
            button1.disabled = true;
            button3.disabled = true;
        }
        restart();
    })

    //continues to next screen
    document.getElementById("button3").addEventListener("click", function (event) {
        //sets names and colours for next screen
        document.querySelector(".t1").innerHTML = p1NameAge;
        document.querySelector(".t1").style.color = p1Colour;
        document.querySelector(".t2").innerHTML = p2NameAge;
        document.querySelector(".t2").style.color = p2Colour;

        //changes screens
        document.querySelector(".first").style.display = "none";
        document.querySelector(".second").style.display = "block";
        title();

    })


    //game screen JavaScript Code


    /**
     * Function that randomly picks the first player to move
     * No parameters or return
     */
    function start(event) {
        if (Math.random() < 0.5) {
            turn = 1;
        } else {
            turn = 2;
        }
        title();
    }

    /**
     * Function that changes the user turn
     * No parameters or return
     */
    function title(event) {
        if (turn === 1) {

            document.querySelector(".turn").innerHTML = p1NameAge + "'s Turn";
            document.querySelector(".turn").style.color = p1Colour;
            document.querySelector(".turnPic").src = "images/x.jpg"

        } else {

            document.querySelector(".turn").innerHTML = p2NameAge + "'s Turn";
            document.querySelector(".turn").style.color = p2Colour;
            document.querySelector(".turnPic").src = "images/o.jpg"

        }
    }

    /**
     * Function that changes places a users mark on the grid
     * Takes 3 parameters i,j,p
     * i and j are the locations of the cell in the board array
     * p is the spot on the grid to be changed
     * returns nothing if turn == 0(game is a draw)
     */
    function move(i, j, p) {
        console.log(board[i][j], i, j, "move", turn)

        if (turn == 0) {
            document.querySelector(".turnPic").src = "images/b.png"
            return
        }
        if (board[i][j] === 'w') {
            if (turn === 1) {
                board[i][j] = 'x';
                p.src = "images/x.jpg";
                turn = 2;

            } else if (turn === 2) {
                board[i][j] = 'o';
                p.src = "images/o.jpg";
                turn = 1;

            }
            //runs if the user does not click on a taken spot
            setTimeout(title(), 100);
            setTimeout(winScreen(), 500);


        }
    }

    //Event listeners for spots on the grid 

    document.querySelector(".onePic").addEventListener("click", function (event) {
        console.log(turn);
        move(0, 0, this);

    });

    document.querySelector(".twoPic").addEventListener("click", function (event) {
        console.log(turn);
        move(0, 1, this);

    });

    document.querySelector(".threePic").addEventListener("click", function (event) {
        console.log(turn);
        move(0, 2, this);

    });

    document.querySelector(".fourPic").addEventListener("click", function (event) {
        console.log(turn);
        move(1, 0, this);

    });

    document.querySelector(".fivePic").addEventListener("click", function (event) {
        console.log(turn);
        move(1, 1, this);

    });

    document.querySelector(".sixPic").addEventListener("click", function (event) {
        console.log(turn);
        move(1, 2, this);

    });

    document.querySelector(".sevenPic").addEventListener("click", function (event) {
        console.log(turn);
        move(2, 0, this);

    });

    document.querySelector(".eightPic").addEventListener("click", function (event) {
        console.log(turn);
        move(2, 1, this);

    });

    document.querySelector(".ninePic").addEventListener("click", function (event) {
        console.log(turn);
        move(2, 2, this);


    });

    /**
     * Function that changes the display if the round has a winner or is a draw
     * No parameters or return
     */
    function winScreen(event) {
        if (checkWin() === 1) {
            //runs if there is a winner
            pauseGame();
            document.querySelector(".turnPic").src = "images/b.png"
            document.querySelector(".turn").innerHTML = win + " wins the game!"
            if (win === p1NameAge) {
                winCount1++;
                document.querySelector(".turn").style.color = p1Colour;
                document.querySelector(".s1").innerHTML = winCount1;
            } else {
                winCount2++;
                document.querySelector(".turn").style.color = p2Colour;
                document.querySelector(".s2").innerHTML = winCount2;
            }
        } else if (checkWin() === 0) {
            //runs if there is a draw
            document.querySelector(".turn").innerHTML = "Draw!"
        }
    }

    /**
     * Function that checks if the game has a winner
     * No parameters 
     * Returns 0 if the game is a draw, 1 if the game has a winner, and 2 if the game should continue
     */
    function checkWin(event) {
        //checks for horizontal win condition 
        for (let i = 0; i < board.length; i++) {
            for (let j = 0; j < 1; j++) {

                if (board[i][j] !== "w" && board[i][j] === board[i][j + 1] && board[i][j] === board[i][j + 2]) {
                    if (board[i][j] === "o") {
                        win = p2NameAge;
                    } else {
                        win = p1NameAge;
                    }
                    return 1;
                }
            }
        }

        //checks for vertical win condition
        for (let j = 0; j < 3; j++) {
            if (board[0][j] !== "w" && board[0][j] === board[1][j] && board[0][j] === board[2][j]) {
                if (board[0][j] === "o") {
                    win = p2NameAge;
                } else {
                    win = p1NameAge;
                }
                return 1;
            }
        }

        //checks for diagonal win condition

        if (board[0][0] !== "w" && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if (board[1][1] === "o") {
                win = p2NameAge;
            } else {
                win = p1NameAge;
            }
            return 1;
        } else if (board[2][0] !== "w" && board[2][0] === board[1][1] && board[2][0] === board[0][2]) {
            if (board[1][1] === "o") {
                win = p2NameAge;
            } else {
                win = p1NameAge;
            }
            return 1;
        }

        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {

                if (board[i][j] == "w") {
                    //returns 2 if the game should continue
                    return 2;
                }
            }
        }
        //returns 0 if it is a  draw 
        return 0

    }


    /**
     * Function that prevents users from placing any marks if there is a winner(ends the game)
     * No parameters or return
     */
    function pauseGame(event) {
        turn = 0;
    }


    //goes back to login page
    document.getElementById("back").addEventListener("click", function (event) {
        document.querySelector(".second").style.display = "none";
        document.querySelector(".first").style.display = "block";
    });

    //new round
    document.getElementById("clear").addEventListener("click", function (event) {
        clearBoard();
    });

    //restart game
    document.getElementById("restart").addEventListener("click", function (event) {
        restart();
    });

    //goes to instructions screen
    document.getElementById("help").addEventListener("click", function (event) {
        document.querySelector(".second").style.display = "none";
        document.querySelector(".third").style.display = "block";
    });

    //goes back to game from instructions screen
    document.getElementById("back2").addEventListener("click", function (event) {
        document.querySelector(".third").style.display = "none";
        document.querySelector(".second").style.display = "block";

    });

    /**
     * Function that restarts the game
     * No parameters or return
     */
    function restart(event) {
        winCount1 = 0;
        winCount2 = 0;
        document.querySelector(".s1").innerHTML = winCount1;
        document.querySelector(".s2").innerHTML = winCount2;
        clearBoard();
    }

    /**
     * Function that clears marks off of the board
     * No parameters or return
     */
    function clearBoard(event) {
        for (let i = 0; i < 3; i++) {
            for (let j = 0; j < 3; j++) {
                board[i][j] = "w";
            }
        }
        document.querySelector(".onePic").src = "images/w.png";
        document.querySelector(".twoPic").src = "images/w.png";
        document.querySelector(".threePic").src = "images/w.png";
        document.querySelector(".fourPic").src = "images/w.png";
        document.querySelector(".fivePic").src = "images/w.png";
        document.querySelector(".sixPic").src = "images/w.png";
        document.querySelector(".sevenPic").src = "images/w.png";
        document.querySelector(".eightPic").src = "images/w.png";
        document.querySelector(".ninePic").src = "images/w.png";
        start();
    }


});