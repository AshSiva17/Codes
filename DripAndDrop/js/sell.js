
/**
author: Jemuel Lateo, Ashwin Sivabalan, Omar Alshantout, Shourya Jeswani
date: 2025-04-24
JS for sells page
 */

window.addEventListener("load", function(evetn){

    let input = document.getElementById("inputImg");
    let preview = document.getElementById("preview");

    // put uploaded image in images
    input.addEventListener("change", function(event){
        // get image file
        let file = this.files[0];
        if (file){
            let reader = new FileReader();

            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
            }

            reader.readAsDataURL(file);
        }
    });
});
