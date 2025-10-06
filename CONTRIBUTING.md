# 🌟 Contributing to QuoteVerse

Thank you for your interest in contributing to **QuoteVerse**!  
This project is built to be **beginner-friendly** and open for **Hacktoberfest 2025** participants.  
Your contributions — big or small — are always welcome! 💬  

---

## 🚀 How to Contribute

1. **Fork** this repository and **clone your fork**.
2. Create a new branch for your work:
   ```bash
   git checkout -b feat/add-quote-yourname

3. Make small, focused changes. Good contributions:
   - Add or improve a quote in `data/quotes.json`.
   - Fix typos in README or docs.
   - Improve developer instructions or add tests.
4. Commit with a clear message and push your branch.
5. Open a Pull Request to `main` and follow the PR template.

## Adding a quote

- Open `data/quotes.json`.
- Add a new object to the array with the following shape:

```json
{
  "id": 3,
  "text": "Your quote text here.",
  "author": "Author Name",
  "tags": ["motivation", "coding"]
}
```

- Ensure `id` is unique. Keep entries small and use proper JSON formatting.

## Running locally (summary)

- Backend (Spring Boot):
  - Use the included `mvnw` wrapper (Windows `mvnw.cmd`) or your local Maven install.
  - Typical command: `./mvnw spring-boot:run` (or `mvnw.cmd spring-boot:run` on Windows).
- Frontend (React):
  - `npm install` and `npm start` in the `frontend` folder.

If the project uses different folders, check the README for exact paths.

## PR checklist

- [ ] I followed the contribution guide.
- [ ] My change is one logical unit.
- [ ] I added/updated documentation if needed.
- [ ] I ran the app locally to verify there are no errors.

## Communication

If you need help, open an issue with the `help wanted` or `good first issue` label and describe what you're trying to do.

Thank you — maintainers appreciate your time and contributions!
