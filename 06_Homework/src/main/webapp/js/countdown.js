var countdown = document.getElementById("countdown");

function update() {
    var now = new Date();
    var start = new Date("2025-09-01T00:00:00");
    var target = new Date("2025-11-30T23:59:59");
    target.setFullYear(now.getFullYear());
    start.setFullYear(now.getFullYear());

    var remaining = target.getTime() - now.getTime();

    var days = Math.floor(remaining / (1000 * 60 * 60 * 24));
    var hours = Math.floor(
        (remaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60),
    );
    var minutes = Math.floor((remaining % (1000 * 60 * 60)) / (1000 * 60));

    countdown.innerText = `${days}дн ${hours}ч ${minutes}м`;
}

update();
setInterval(() => {update();}, 60000);