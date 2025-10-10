const slides = document.querySelector('.Slides');
const allSlides = document.querySelectorAll('.Slide_photo');
const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');
let currentSlide = 0;

function showSlide(index) {
    if (index < 0) {
        index = allSlides.length - 1;
    }
    if (index >= allSlides.length) {
        index = 0;
    }
    currentSlide = index;
    slides.style.transform = `translateX(-${index * 100}%)`;
}

function showPrev() {
    showSlide(currentSlide - 1);
}

function showNext() {
    showSlide(currentSlide + 1);
}