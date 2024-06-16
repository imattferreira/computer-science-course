import { twJoin } from "tailwind-merge";
import { ArrowRight, type LucideIcon } from "lucide-react";
import NextLink from "next/link";

interface ItemProps {
  active: boolean;
  icon: LucideIcon;
  link: string;
  title: string;
}

function Item({ active, icon: Icon, link, title }: ItemProps) {
  return (
    <>
      <NextLink
        href={link}
        className={twJoin(
          "flex items-center hover:bg-zinc-950 hover:text-emerald-600 transition-all duration-200 px-2 py-3 rounded-md gap-x-2 w-full",
          active ? "text-gray-100" : "text-gray-300/90"
        )}
      >
        <div className="flex gap-x-3 w-full">
          <Icon />
          <p>{title}</p>
        </div>
        <ArrowRight size={20} />
      </NextLink>
    </>
  );
}

export default Item;
