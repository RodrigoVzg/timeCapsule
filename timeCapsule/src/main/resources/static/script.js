document.addEventListener('DOMContentLoaded', function () {
  const form = document.getElementById('formCapsule');
  const returns = document.getElementById('returns');

  form.addEventListener('submit', async function (event) {
    event.preventDefault(); 

    const email = document.getElementById('email').value.trim();
    const message = document.getElementById('message').value.trim();
    const date = document.getElementById('date').value;
    const hiddenField = document.getElementById('hiddenField').value.trim();

    const now = new Date();
    now.setHours(0, 0, 0, 0); 

    const dateSelect = new Date(date);

    if (dateSelect <= now) {
      returns.textContent = '⚠️ The submission date must be in the future.';
      returns.style.color = 'red';
      return;
    }

    const data = {
      destinationEmail: email,
      message: message,
      dateSent: date,

      hiddenField: hiddenField
    };

    try {
      const response = await fetch('/capsule', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        const text = await response.text();
        returns.textContent = "Time capsule saved";
        returns.style.color = 'green';
        form.reset();
      } else {
        const error = await response.text();
        returns.textContent = `❌ Error: ${error}`;
        returns.style.color = 'red';
      }
    } catch (error) {
      returns.textContent = '❌ Error connecting to server.';
      returns.style.color = 'red';
      console.error(error);
    }
  });
});
