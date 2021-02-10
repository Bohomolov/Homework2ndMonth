var canvas = document.getElementById("drawingCanvas");
var ctx = canvas.getContext('2d');
var myColor = 'black'

document.getElementById('color').oninput = function () {
    myColor = this.value;
}

canvas.onmousedown = function (event) {
    canvas.onmousemove = function (event) {
        var x = event.offsetX;
        var y = event.offsetY;

        ctx.fillRect(x - 5, y - 5, 10, 10);
        ctx.lineWidth = 1;

        ctx.fillStyle = myColor;
        ctx.fill()
    }
    canvas.onmouseup = function () {
        canvas.onmousemove = null;
    }
}

function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
}

function saveCanvas() {
    var imageCopy = document.getElementById("savedImage");
    imageCopy.src = canvas.toDataURL();
    var imageContainer = document.getElementById("savedContainer");
    imageContainer.style.display = "block";
}

function changeThickness(thickness) {
    ctx.lineWidth = thickness;
}