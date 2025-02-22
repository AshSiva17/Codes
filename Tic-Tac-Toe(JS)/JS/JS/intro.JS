/**
 * class to represent a player object
 * Sam Scott, McMaster 2025
 */
//variable that determines if the user is playing against a bot
let game;
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

    // some important DOM elements
    let namefield = document.getElementById("name");
    let colourfield = document.getElementById("colour");
    let agefield = document.getElementById("age");
    let playerbox = document.getElementById("players");
    let numplayers = document.getElementById("numplayers");


    /*
     * Button listener to add and render players
     */
    document.getElementById("button1")
        .addEventListener("click", function (event) {
            // create player and add to array
            let p = new Player(namefield.value, colourfield.value, parseInt(agefield.value));
            players.push(p);
            console.log(JSON.stringify(players));

            // modify the header of the players section
       
            if (players.length > 0) {
                button3.disabled = false;
                button2.disabled = false;
            }
            if (players.length == 2) {
                namefield.disabled = true;
                agefield.disabled = true;
                colourfield.disabled = true;
                button1.disabled = true;
                button2.disabled = true;
                button4.disabled = false;
            }

            // render the players
            playerbox.innerHTML = "";
            for (let player of players) {
                playerbox.appendChild(player.render());
            }

        });

        document.getElementById("button2")
        .addEventListener("click", function (event) {
            // create player and add to array
            let p = new Player("Bot", "Green", 12);
            players.push(p);
            console.log(JSON.stringify(players));

            // modify the header of the players section
       
            if (players.length > 0) {
                button3.disabled = false;
            }
            if (players.length == 2) {
                namefield.disabled = true;
                agefield.disabled = true;
                colourfield.disabled = true;
                button1.disabled = true;
                button2.disabled = true;
                button4.disabled = false;
            }

            // render the players
            playerbox.innerHTML = "";
            for (let player of players) {
                playerbox.appendChild(player.render());
            }

        });

        document.getElementById("button3").addEventListener("click",function(event){
            players.pop()
            playerbox.innerHTML = "";
            for (let player of players) {
                playerbox.appendChild(player.render());
            }
            if (players.length == 0) {
                button3.disabled = true;
            }
            if (players.length < 2) {
                namefield.disabled = false;
                agefield.disabled = false;
                colourfield.disabled = false;
                button1.disabled = false;
                button2.disabled = false;
                button4.disabled = true;
            }
        })

        document.getElementById("button4").addEventListener("click",function(event){
            document.location.href = "game.html";
        })




});