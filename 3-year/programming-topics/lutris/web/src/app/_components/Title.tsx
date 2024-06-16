import { LucideIcon } from "lucide-react";

interface TitleProps {
  icon: LucideIcon;
  children: string;
}

function Title({ icon: Icon, children }: TitleProps) {
  return (
    <h1 className="flex gap-x-2 text-2xl items-center mb-8">
      <Icon />
      {children}
    </h1>
  );
}

export default Title;
