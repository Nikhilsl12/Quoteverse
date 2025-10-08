import logo from "../assets/logo.png";

const Header = () => {
  return (
    <header className="flex flex-col gap-4 items-center py-8 text-center bg-secondary">
      <div className="flex gap-2 md:gap-4">
        <img className="size-14 md:size-16" src={logo} alt="quoteverse logo" />
        <div className="font-tourney bold text-3xl lg:text-4xl my-4">
          QUOTEVERSE
        </div>
      </div>
      <h1 className="text-2xl lg:text-3xl">
        &#10077; Discover and share your favorite quotes. &#10078;
      </h1>
    </header>
  );
};

export default Header;
