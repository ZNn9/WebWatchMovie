document.addEventListener('DOMContentLoaded', function () {
    const miniDetailsBtn = document.getElementById('mini-details-btn');
    const miniDetailsMenu = document.getElementById('mini-details-menu');
    const toggleMiniDetails = document.getElementById('toggle-mini-details');

    miniDetailsBtn.addEventListener('click', () => {
        miniDetailsMenu.classList.toggle('active');
    });

    toggleMiniDetails.addEventListener('click', () => {
        miniDetailsMenu.classList.toggle('hidden');
    });

    let lastScrollY = window.scrollY;
    let targetTop = window.scrollY + 20;
    const miniDetails = document.querySelector('.mini-details');

    window.addEventListener('scroll', () => {
        targetTop = window.scrollY + 20;
    });

    function smoothScroll() {
        lastScrollY += (targetTop - lastScrollY) * 0,4; // Điều chỉnh hệ số 0.1 để giảm tốc độ di chuyển
        miniDetails.style.top = `${lastScrollY}px`;
        requestAnimationFrame(smoothScroll);
    }

    smoothScroll();
});