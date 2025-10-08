const QuoteCard = ({ quote }) => {
  const { text, author, tags } = quote;
  return (
    <div className="flex flex-col justify-center gap-4 p-4 text-center bg-surface border-border border-2 rounded-lg shadow-lg shadow-gray-600">
      <h3 className="text-xl bold md:text-2xl"> &#10077; {text} &#10078;</h3>
      <p className="font-bold text-xl"> - {author}</p>
      <p># {tags.join(" #")}</p>
    </div>
  );
};

export default QuoteCard;
