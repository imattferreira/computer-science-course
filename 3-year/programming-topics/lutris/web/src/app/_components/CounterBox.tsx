interface CounterBoxProps {
  counter: number;
  title: string;
}

function CounterBox({ counter, title }: CounterBoxProps) {
  return (
    <div className="relative rounded-md bg-zinc-950 h-60 p-4 flex items-center justify-center">
      <h3 className="absolute top-3 left-3 text-emerald-600 text-xs">
        {title}
      </h3>
      <p className="text-4xl text-gray-200">{counter}</p>
    </div>
  );
}

export default CounterBox;
