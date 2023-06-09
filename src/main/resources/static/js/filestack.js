(function() {
    "use strict"


    /*
    currently not working. seems to be an issue with picker and filestack.
    my guess is the imported script from filestack isn't getting to the variables fast enough
    causing issues with those variables not being found.
    leaving this here for now so in case i have time to revisit the problem
     */


// Set up the picker
const client = filestack.init(FILESTACK_KEY);
const options = {
    fromSources: ["local_file_system", "instagram", "facebook","picasa"],
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
};
const picker = client.picker(options);

// Get references to the DOM elements

const form = document.getElementById('pick-form');
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');
const urlBox = document.getElementById('urlBox');

// Add our event listeners

btn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

form.addEventListener('submit', function (e) {
    e.preventDefault();
    alert('Submitting: ' + fileInput.value);
});

// Helper to overwrite the field input value

function updateForm (result) {
    const fileData = result.filesUploaded[0];
    fileInput.value = fileData.url;

    // If file is resizable image, resize and embed it as a thumbnail preview
    if (['jpeg', 'png'].indexOf(fileData.mimetype.split('/')[1]) !== -1) {
        const container = document.getElementById('thumbnail-container');
        const thumbnail = document.getElementById('thumbnail') || new Image();
        thumbnail.id = 'thumbnail';
        thumbnail.src = client.transform(fileData.handle, {
            resize: {
                width: 200
            }
        });

        if (!container.contains(thumbnail)) {
            container.appendChild(thumbnail);
        }
    }

    console.log(JSON.stringify(result))
    // Some ugly DOM code to show some data.
    const name = document.createTextNode('Selected: ' + fileData.filename);
    const url = document.createElement('a');
    url.href = fileData.url;
    url.appendChild(document.createTextNode(fileData.url));
    nameBox.appendChild(name);
    urlBox.appendChild(document.createTextNode('Uploaded to: '));
    urlBox.appendChild(url);
};

})();