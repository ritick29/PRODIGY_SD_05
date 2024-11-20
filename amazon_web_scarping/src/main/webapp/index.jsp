<!DOCTYPE html>
<html>
<head>
    <title>Amazon Web Scraper</title>
    <style>
    /* General Reset */
    body, h1, label, input, button, a {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    /* Body Styling */
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f5f5f5;
        color: #333;
        padding: 20px;
    }

    /* Container Styling */
    form {
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* Form Header */
    h1 {
        text-align: center;
        color: #444;
        margin-bottom: 20px;
        font-size: 24px;
    }

    /* Label Styling */
    label {
        display: block;
        font-weight: bold;
        margin-bottom: 8px;
        font-size: 14px;
    }

    /* Input Field Styling */
    input[type="text"] {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus {
        outline: none;
        border-color: #0073e6;
    }

    /* Button Styling */
    button {
        display: block;
        width: 100%;
        padding: 10px 20px;
        background-color: #0073e6;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #005bb5;
    }

    /* Footer Link Styling */
    a {
        display: inline-block;
        margin-top: 15px;
        text-align: center;
        color: #0073e6;
        text-decoration: none;
        font-size: 14px;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
    
</head>
<body>
    <h1>Amazon Web Scraper</h1>
    <form action="ScrapeAmazon" method="post">
        <label for="searchUrl">Enter Amazon Search URL:</label><br>
        <input type="text" id="searchUrl" name="searchUrl" placeholder="https://www.amazon.com/s?k=laptops" required><br><br>
        <button type="submit">Scrape</button>
    </form>
</body>
</html>
