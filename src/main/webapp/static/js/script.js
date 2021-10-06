document.querySelectorAll(".minus-btn")
    .forEach(btn => btn.addEventListener("click", function() {
        this.nextElementSibling.value = parseInt(this.nextElementSibling.value) - 1;
    }));

document.querySelectorAll(".plus-btn")
    .forEach(btn => btn.addEventListener("click", function() {
        this.previousElementSibling.value = parseInt(this.previousElementSibling.value) + 1;
    }));