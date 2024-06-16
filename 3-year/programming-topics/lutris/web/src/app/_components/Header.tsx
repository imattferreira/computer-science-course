import { Handshake } from "lucide-react";
import NextLink from "next/link";

function Header() {
  return (
    <header className="w-full flex items-center justify-center h-16 px-10 border-b border-b-zinc-600">
      <div className="w-full">
        <NextLink href="/" className="block size-fit">
          <Handshake size={28} className="text-emerald-600" />
        </NextLink>
      </div>
    </header>
  );
}

export default Header;
