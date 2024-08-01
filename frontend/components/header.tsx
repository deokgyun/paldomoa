import Link from 'next/link'
import React from 'react'

const navItems = [
  { href: '/recruit', label: 'About' },
  { href: '/match', label: '매칭' },
  { href: '/market', label: '중고거래' },
  { href: '/my', label: '팀관리' },
]

function Logo() {
  return (
    <Link className="text-2xl font-bold" href="/">
      Logo
    </Link>
  )
}

function Navigation() {
  return (
    <nav className="flex space-x-4">
      {navItems.map((item) => (
        <Link key={item.href} href={item.href} className="text-black hover:underline">
          {item.label}
        </Link>
      ))}
    </nav>
  )
}

function AuthLinks() {
  return (
    <div className="flex space-x-2">
      <Link href="/login" className="px-4 py-2 rounded hover:bg-gray-100">
        <span className="text-black text-[14px] leading-[17px]">로그인</span>
      </Link>
      <Link href="/signin" className="px-4 py-2 bg-blue-500 rounded hover:bg-blue-700">
        <span className="text-white text-[14px] leading-[17px]">회원가입</span>
      </Link>
    </div>
  )
}

export default function Header() {
  return (
    <header className="w-full max-w-7xl mx-auto flex justify-between items-center py-4">
      <Logo />
      <Navigation />
      <AuthLinks />
    </header>
  )
}
