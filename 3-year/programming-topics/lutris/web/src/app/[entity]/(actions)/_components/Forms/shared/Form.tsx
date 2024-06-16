interface FormProps {
  children: React.ReactNode;
  onSubmit: () => void;
}

function Form({ children, onSubmit }: FormProps) {
  return (
    <form
      className="bg-zinc-950 px-6 py-4 rounded-md max-w-screen-md gap-y-4 flex flex-col mx-auto"
      onSubmit={(event) => {
        event.preventDefault();
        event.stopPropagation();
        // @ts-ignore
        onSubmit(event);
      }}
    >
      {children}
    </form>
  );
}

export default Form;
