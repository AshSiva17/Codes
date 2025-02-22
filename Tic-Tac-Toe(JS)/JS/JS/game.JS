let board = [['w', 'w', 'w'], ['w', 'w', 'w'], ['w', 'w', 'w']]

let player1 = 'X';
let player2 = 'O'

let turn;

window.addEventListener("load", function (event) {

  
  function start(event) {
    if (Math.random() < 0.5) {
      turn = 1;
    } else {
      turn = 2;
    }
  }
  start();
  function title(event) {
    if (turn == 1) {
      document.querySelector("h1").innerHTML = "Player 1's Turn";

    } else {
      document.querySelector("h1").innerHTML = "Player 2's Turn";
    }
  }
  title();
  function move(i, j, p) {
    if (turn == 1 && board[i][j] == 'w') {
      board[i][j] = 'x';
      p.src = "images/x.jpg";
      turn = 2;
    } else if (turn == 2 && board[i][j] == 'w') {
      p.src = "images/o.jpg";
      board[i][j] = 'o';
      turn = 1;
    }
    title();
  }
  document.querySelector(".onePic").addEventListener("click", function (event) {
    move(0, 0, this)
  });

  document.querySelector(".twoPic").addEventListener("click", function (event) {
    move(0, 1, this)
  });

  document.querySelector(".threePic").addEventListener("click", function (event) {
    move(0, 2, this)
  });

  document.querySelector(".fourPic").addEventListener("click", function (event) {
    move(1, 0, this)
  });

  document.querySelector(".fivePic").addEventListener("click", function (event) {
    move(1, 1, this)
  });

  document.querySelector(".sixPic").addEventListener("click", function (event) {
    move(1, 2, this)
  });

  document.querySelector(".sevenPic").addEventListener("click", function (event) {
    move(2, 0, this)
  });

  document.querySelector(".eightPic").addEventListener("click", function (event) {
    move(2, 1, this)
  });

  document.querySelector(".ninePic").addEventListener("click", function (event) {
    move(2, 2, this)
  });


  

});
