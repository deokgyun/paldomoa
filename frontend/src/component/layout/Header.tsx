import React from "react";

interface HeaderProps {
    isLoggedIn: boolean;
}

const Header: React.FC<HeaderProps> = ({isLoggedIn}) => {

    return (
        <header className="w-full max-w-7xl mx-auto flex justify-between items-center py-4">
            <div className="text-2xl font-bold">Logo</div>
            <nav className="flex space-x-4">
                <a href="#" className="text-black hover:underline">Menu 1</a>
                <a href="#" className="text-black hover:underline">Menu 2</a>
                <a href="#" className="text-black hover:underline">Menu 3</a>
                <a href="#" className="text-black hover:underline">Menu 4</a>
            </nav>
            <div className="flex space-x-2">
                {isLoggedIn ? (
                    <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">정보보기</button>
                ) : (
                    <>
                        <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">로그인</button>
                        <button className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">회원가입</button>
                    </>
                )}
            </div>
        </header>
    )
}

export default Header