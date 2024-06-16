import type { Metadata } from "next";
import { Inter } from "next/font/google";
import Header from "./_components/Header";
import { twJoin } from "tailwind-merge";
import "./globals.css";
import Sidebar from "./_components/Sidebar";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Lutris",
  description: "Gerencie a sua empresa com uma interface simples e amigável",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={twJoin(
          "bg-zinc-900 text-gray-200 h-screen",
          inter.className
        )}
      >
        <Header />
        <div className="flex">
          <Sidebar />
          <main className="py-6 px-10 w-[calc(100vw_-_80px)]">{children}</main>
        </div>
      </body>
    </html>
  );
}
