import Create from "./Create";
// import SearchInput from "./SearchInput";

function Toolbar() {
  return (
    <div className="p-4 bg-zinc-950 rounded-md w-full max-w-screen-xl mx-auto flex gap-x-4 justify-end">
      {/* <SearchInput /> */}
      <Create />
    </div>
  );
}

export default Toolbar;
