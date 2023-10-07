const fileInput = document.getElementById('{fileId}');
fileInput.addEventListener('change', function () {
    const file = fileInput.files[0];
    const reader = new FileReader();
    reader.onload = function () {
        const hiddenInput = document.getElementById('{hiddenId}');
        hiddenInput.value = reader.result;
        $0.$server.receiveFile(reader.result);
    };
    reader.readAsDataURL(file);
});
fileInput.click();
